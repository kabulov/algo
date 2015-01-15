

#include <regex>
#include <string>
#include <vector>
#include <iostream>
#include <stdexcept>
#include <iomanip>
#include <sstream>

void usage() {
	std::cout <<  "---usage---" "\n";
	std::cout << 
		" 3 parameters required :" "\n"
		"  1st : time - HH:MM (00..23|00..59) | HH:MM (AM|PM) (00..11|00..59) " "\n"
		"  2nd : angle format - ""\n"
		"        rad = radians | deg = degrees | dms = degrees minutes seconds " "\n"
		"  3rd : clock type - mech = Mechanic | quar = Quartz " "\n" ;
}

std::vector<std::string> SplitString(const std::string& string_to_split, const std::string& regex) {
	std::vector<std::string> result;
	std::tr1::regex separator(regex);
	std::tr1::sregex_token_iterator end_of_sequence;
	std::tr1::sregex_token_iterator token(string_to_split.begin(), string_to_split.end(), separator, -1);
	while(token != end_of_sequence) {
		result.push_back(*token);
		++token;
	}
	return result;
}

struct Time {
	int hours;
	int minutes;
};

class TimeBuilder {
public:
	//return type is a pair : {hours, minutes}, where 0 <= hours < 24 and 0 <= minutes < 60
	static Time BuildTime(const std::string& supposed_to_be_time) 
	{
		std::vector<std::string> split_result = SplitString(supposed_to_be_time, " ");
		if (split_result.size() <= 2) 
		{
			std::string time_type = (split_result.size() == 2) ? 
									split_result[1] : "EMPTY";
			if (time_type == "AM" || 
				time_type == "PM" || 
				time_type == "EMPTY") 
			{
				split_result = SplitString(split_result[0], ":");
				if (split_result.size() == 2 && 
					std::regex_match(split_result[0], std::regex("[0-9]{2}")) && 
					std::regex_match(split_result[1], std::regex("[0-9]{2}"))) 
				{
					int hours = (std::stoi(split_result[0])) + 
								(time_type == "PM" ? 12 : 0);
					int minutes = std::stoi(split_result[1]);
					if (hours < 24 && minutes < 60) 
					{
						Time time = {hours, minutes};
						return time;
					}
				}
			}
		}
		throw std::runtime_error("bad time");
	}
};

class Angle {
public:
	Angle(){}

	Angle(int deg, int min) {
		deg_ = deg;
		min_ = min;
	}

	std::string RadFormat() const {
		double radian = PI() * AngleToDouble() / 180.0;
		std::ostringstream out;
		out << std::fixed << std::setprecision(5) << radian;
		return out.str();
	}	

	std::string DegFormat() const {
		double degrees = AngleToDouble();
		std::ostringstream out;
		out << std::setprecision(5) << degrees;
		return out.str();
	}	

	std::string DMSFormat() const {
		std::string degrees = std::to_string(deg_);
		std::string seconds = "00";
		std::ostringstream out;
		out << std::setw(2) << std::setfill('0') << min_;
		std::string minutes = out.str();
		return degrees + "." + minutes + "\'" + seconds + "\'\'";
	}	

	int getDeg() const {
		return deg_;
	}

	int getMin() const {
		return min_;
	}
private:
	double AngleToDouble() const {
		return (1.0 * deg_) + (min_ == 0 ? 0.0 : 0.5);
	}

	double PI() const {
		return acos(-1.0);
	}

	int deg_;
	int min_;
};

std::string AngleToString(const Angle& angle, const std::string& output_type) {
	if (output_type == "deg") {
		return angle.DegFormat();
	} else if (output_type == "rad") {
		return angle.RadFormat();
	} else if (output_type == "dms") {
		return angle.DMSFormat();
	} 
	
	throw std::runtime_error("bad output type");
}

class Clock {
public:
	virtual Angle AngleBetweenArrows() = 0;

	void setTime(const Time& time) {
		time_ = time;
	}

	void setTime(const int hours, const int minutes) {
		time_.hours = hours;
		time_.minutes = minutes;
	}

	static const int DEGREES_IN_MINUTE = 6;
	static const int DEGREES_IN_HOUR = 30;
	static const int FULL_ANGLE = 360;

protected:
	Time time_;

};

class MechanicClock : public Clock {
public:
	virtual Angle AngleBetweenArrows() { 
		time_.hours %= 12;
		int half = time_.minutes % 2;
		int minute_degrees = time_.minutes * Clock::DEGREES_IN_MINUTE;
		int hour_degrees = time_.minutes / 2 + time_.hours * Clock::DEGREES_IN_HOUR;
		int degrees = (minute_degrees - hour_degrees - half + Clock::FULL_ANGLE) % Clock::FULL_ANGLE;	
		degrees = std::min(degrees, Clock::FULL_ANGLE - (degrees + half));
		int minutes = Clock::DEGREES_IN_HOUR * (time_.minutes % 2);
		return Angle(degrees, minutes);
	}
};

class QuartzClock : public Clock {
public:
	virtual Angle AngleBetweenArrows() { 
		time_.hours %= 12;
		int hour_degrees = time_.hours * Clock::DEGREES_IN_HOUR;
		int minute_degrees = time_.minutes * Clock::DEGREES_IN_MINUTE;
		int degrees = (minute_degrees - hour_degrees + Clock::FULL_ANGLE) % Clock::FULL_ANGLE;
		degrees = std::min(degrees, Clock::FULL_ANGLE - degrees);
		return Angle(degrees, 0);
	}
};

class ClockBuilder {
public:
	static Clock* BuildClock(const std::string& clock_type) {
		if (clock_type == "quar")
			return new QuartzClock();
		else if (clock_type == "mech")
			return new MechanicClock();
	
		throw std::runtime_error("bad clock type");
	}
};

void ProcessCMD(int argc, char** argv) {
	if (argc != 4) {
		throw std::runtime_error("expected 3 arguments");		
	}

	std::string supposed_to_be_time(argv[1]);
	std::string output_type(argv[2]);
	std::string clock_type(argv[3]);

	Clock* clock = ClockBuilder::BuildClock(clock_type);
	Time time = TimeBuilder::BuildTime(supposed_to_be_time);
	clock->setTime(time);
	Angle angle = clock->AngleBetweenArrows();
	std::string output_string = AngleToString(angle, output_type);

	std::cout << output_string << std::endl;
}

int RunAllUnitTests();

int main(int argc, char** argv) {
#ifdef TEST_MODE
	return RunAllUnitTests();
#endif

	try {
		ProcessCMD(argc, argv);
	} catch (std::exception& e) {
		std::cout << e.what() << std::endl;
		usage();
		return 1;
	}

	return 0;
}

void BuildTimeTest();
void MechanicClockTest();
void QuartzClocKTest();

int RunAllUnitTests() {	
	try {
		MechanicClockTest();
		QuartzClocKTest();
	    BuildTimeTest();
	} catch (std::exception& e) {
		std::cout << e.what() << std::endl;
		return 1;
	}

	return 0;
}

template<class Type> 
void REQUIRE_EQUAL(const Type& first, const Type& second, const std::string& message = "") {
	if (!(first == second)) {
		std::ostringstream log;
		log << "REQUIRE_EQUAL failed : " 
			<< first << "!= " << second 
			<< " " << message << "\n";
		throw std::runtime_error(log.str());
	}
}

std::string time_to_string(int hours, int minutes) {
	std::ostringstream h, m;
	h << std::setw(2) << std::setfill('0') << hours;
	m << std::setw(2) << std::setfill('0') << minutes;
	return h.str() + ":" + m.str();
}

void BuildTimeTest() {
	Time time;
	std::string time_as_string;
	for (int hours = 0; hours < 24; ++hours) {
		for (int minutes = 0; minutes < 60; ++minutes) {
			time_as_string = time_to_string(hours, minutes);
			time = TimeBuilder::BuildTime(time_as_string);

			REQUIRE_EQUAL(hours, time.hours, "BuildTimeTest : bad hours");
			REQUIRE_EQUAL(minutes, time.minutes, "BuildTimeTest : bad minutes");

			time_as_string = time_to_string(hours % 12, minutes);
			time_as_string.append((hours < 12 ? " AM" : " PM"));
			time = TimeBuilder::BuildTime(time_as_string);			

			REQUIRE_EQUAL(hours, time.hours, "BuildTimeTest : bad hours");
			REQUIRE_EQUAL(minutes, time.minutes, "BuildTimeTest : bad minutes");
		}
	}
}

enum ClockType{mechanic, quartz};

int AngleBetweenArrowsDegrees(int hours, int minutes, ClockType type) {
	hours %= 12;
	if (type == quartz) {
		for (int degrees = 0; degrees < 360; degrees += 6) {
			int minute_degrees = (hours * 30 + degrees) % 360;
			if (minute_degrees / 6 == minutes) {
				return std::min(degrees, 360 - degrees);
			}	
		}
		throw std::runtime_error("invalid time in AngleBetweenArrowsDegrees");
	} else {
		int hour_degrees = hours * 30 + minutes / 2;
		int minute_degrees = minutes * 6;
		int degrees = std::abs(hour_degrees - minute_degrees);
		if (hour_degrees < minute_degrees && minutes % 2 == 1) {
			--degrees;
		}
		if (degrees >= 180) {
			degrees = 360 - degrees;
			if (minutes % 2 == 1) {
				--degrees;
			}
		}
		return degrees;
	}
}

int AngleBetweenArrowsMinutes(int hours, int minutes, ClockType type) {
	if (type == quartz) {
		return 0;	
	} else {
		return 30 * (minutes % 2);
	}
}

void ClockTest(Clock* clock, ClockType type) {
	Angle angle;
	for (int hours = 0; hours < 24; ++hours) {
		for (int minutes = 0; minutes < 60; ++minutes) {
			clock->setTime(hours, minutes);
			angle = clock->AngleBetweenArrows();
			
			int true_angle_degrees = AngleBetweenArrowsDegrees(hours, minutes, type);
			int true_angle_minutes = AngleBetweenArrowsMinutes(hours, minutes, type);
			int trial_angle_degrees = angle.getDeg();
			int trial_angle_minutes = angle.getMin();

			REQUIRE_EQUAL(true_angle_degrees, trial_angle_degrees, "ClockTest : bad degrees");
			REQUIRE_EQUAL(true_angle_minutes, trial_angle_minutes, "ClockTest : bad minutes");
		}
	}
}

void MechanicClockTest() {
	Clock *clock = new MechanicClock();
	ClockTest(clock, mechanic);
}

void QuartzClocKTest() {
	Clock *clock = new QuartzClock();
	ClockTest(clock, quartz);
}

