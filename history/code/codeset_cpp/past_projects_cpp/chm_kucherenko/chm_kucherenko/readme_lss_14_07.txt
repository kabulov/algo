������� ����� (����� � ������ 14)

����������� :

A - �������(�������� ����������) �������
B - ������ ��������� ������
X - ������ ����������� 
n - ����������� A
tmp - ��������������� ������

���� :

������� ������� �������� ��������� ������� LU-���������� �������� �������.

��������� �������:

3 ����� :
 -main_lss_14_07.c : ����� ����������� ����� ����� ������� - ������� main, � ������� ���������� ������������ ����� �������. 
  ��������� � ��������� ����� ����������, �������� �������� �������������� �������(����) ��� ���������� ������. 
 -lss_14_07.h : �������� �������� �������������� �������(lss_14_07), � �������� ������������ ������������(ON) ��� �������������(OFF) ����� ����������.
 -lss_14_07.c : ���������� �������� �������������� �������, ������� ���������� LU ���������� �������� ������� � ������ ������� ���������.

��������� ������:

--main_lss_14_07.c--

 1)������������ ������
 2)���������, ���������� ����� ��������(DEFAULT_INPUT_FILE_NAME) � ���������(DEFAULT_OUTPUT_FILE_NAME) ������ �� ���������
 3)���������� ���������� ���������� �������� ������ -d(debug), -e(error), -p(printmtx), -t(printextm). ����� ��������� �������� ON ��� OFF.
 4)�������:
  -int usage(FILE* f) - � ���� f ������� "���������� �� ���������� ���������"(���������� 0) 	
  -int inputfileformat(FILE* f) - � ���� f ������� ������ �������� �����(���������� 0)
  -size_t lss_memsize_14_07(int n) - ���������� ������ �������������� ������ ����������� ��� ������� � �������� �������� ������� n 	
  -int main(int argc, char** argv) - ������� ����������� ������������ ����� �������, ��� 
   argc - ���������� ������������ ��������� ����������(����� ������������� �����) 
   argv - ������������ ��������� ���������(������ �����) 

--lss_14_07.h--
 
 1)����������� �������� ������ : ON == 1, OFF == 0
 2)�������� �������� �������������� ������� :
  int lss_14_07(int n, double* A, double* B, double* X, double* tmp)
  -������� ��������� ������� � ������������
  -������ tmp ������������ � ������� lss_memsize_14_07  
  -������� ����������:
    0 - ��� �������� ������� �������
    (������ ���� ������������ �������� ������� �� ����� 0, �� ��� ������������� ������������ ������ ���, ����� ������� ������ �� ���� ����� 0)
   -1 - ���� LU-����� �� �������� � �������(������������ ����� 0)
   
--lss_14_07.c--

 1)���������� �������� �������������� ������� :
  -eps ������ ��� ��������� � 0 :
   -��� double ������������ 15 ���� ����� �������.
    � ������������ ������������� ������������ ����� ������ ������������ �����������.
    ���������� ���� double �������� � 15-� ������� ����� ������� ��������� �����������,
    ������� (double)0 < 1e-14, �� �� (double)0 == 0(� �������������� ������).
	� ��������� ����� � ����� ����� ���������� ����� � 0, �� �� ������������� ��������
	���������� ���������� � eps, ��� eps == 1e-14.
  -�� ����� ���������� ��������� ������ �� -d(debug) : 
   -������� �������� �������
   -������� �������� ������� ����� ������������ �����(���� ���� ������������ ����� �� ����)
   -������� LU ������� : 
    L - (i,j), ��� i >= j
    U - (i,j), ��� i < j
   -������� ������� ��������� LY==B
   -������� ������� ��������� UX==Y
  -���������� ��������� ������������� ��������� � ������������(����)
  -�������� A � tmp � ������� ������������, ��� ���� ���������� tmp, ������ tmp n*n
  -��������� A � LU � LU ���������� ������ � ������� A(��������� ����� � A)
  -� �������� ������� �� ��������������� ������ :
   -���������, ��� ��� ������ � �������������� ����������� main
   -�������� ������� �������� �������������� ��������������� �������� ����, ������� ������ ������
 
A������� : 

-���� |A| == 0 �� ����� LU ���������� �� ��������
-����� ��������� A �� LU � ������ : 1.LY=B 2.UX=Y

������:

1)������������ |A|
-������������ ��������� �� ������ ������ � ������� �������� �������� �� �������
-���� ������������ ������� � �������(� ������� � �������� >= ������� ������(i)) � ������� ��������� ������� ������� �������(A[i][i])
-������������ ������(i-� � ��� � ������� ��������)
 -���� ������� �������: A[i][i](A[i*n+i]) == 0 - �� |A| == 0, �������
 -����� ������ A[i] /= A[i][i], ������ A[p] -= A[i] * A[p][i], p = i+1, i+2, .., n-1
2)|A| != 0
-��������� A: A=>LU
-������ LY==B(� ����� Y ��� X)
-������ UX==Y(� ����� Y ��� X, ����� ������� �� ����� �������� ����� ������ Y, ��� � X, ������ �������� ���� � X ������������ �����)

Usage: 
lss [input_file_name] [output_file_name] [options]
Where options include:
  -d    print debug messages [default OFF]
  -e    print errors [default OFF]
  -p    print matrix [default OFF]
  -t    print execution time [default OFF]
  -h, -?     print this and exit
Default input_file_name value is lss_14_07_in.txt, default output_file_name value is lss_14_07_out.txt.

-����� ������ ������� - ������������ ��������� ��������� (������ ��������� ���� ���������� ������� main). ������ ������ ���������:
 -main_lss_14_07 input.txt output.txt -e -t -d
-���� ������� ��� input �����, �� �� ������� ��� output ����� �� ������
-���� ������� ��� output �����, �� �� ������� ��� input ����� �� ������
-�������� input � output ������ ������ ���� ������
-main ������� ����� ���������� ���������(� stdout), ���� �������� ����� -t
-main ������� �������� �������(A)(� stdout), ���� �������� ����� -p
-main ������� ��������� �� �������(� stderr), ���� �������� ����� -e :
 -��������, ���� n == 3, � ������� ������ ��� ������� �� ���� ���������, �� ����� �������� ��������� �� ������
 -�� ��� ����������� ����� -e ����� ��������� �� ����� ��������, ��������� ������ ��������� ����������
-main ������� ���������� ���������(� stderr), ���� �������� ����� -d


