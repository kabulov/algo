
//n-kolichestvo uzlov(tochek) v kotorix izvestno znachenie funkcii
//x-massiv tochek v kotorix izvestno znachenie funkcii
//fx-znacheniya funkcii v sootvetstvuyushix tochkax
//koef-koefficienti interpoliruyushey(priblijayushey) funkcii
//dopolnitelnoy informacii net
int pf_koef(int n, double* x, double* fx, double* koef, double* e);

//vozvrashaet znacheniya v k+1 tochke interpoliruyushey funkcii
double* pf_val(double* koef, double* e, double a, double b, int k);
