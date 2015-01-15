
//help : spravochnik
void usage();

//sintaksicheskaya proverka parametra komandnoy stroki
bool is_unknown_param(char* param);

//razbor komandnoy stroki, nachinaya s 3go parametra v massive argv(1, 2 parametri eto nazvaniya input i output faylov)
int parse_cmd(int argc, char** argv);

 //0 - matrica simmetrichna
 //-1 - matrica ne simmetrichna -> metod uprosheniya ne primenim
int is_sim_13_13(int n, double* A, double precision);
 //v A pri uspeshnom zavershenii sim_13_13 xranitsya : elementi glavnoy diagonali na glavnoy diagonali, a takje
 //elementi pervoy poddiagonali pod diagonalyu, a na meste ostalnix elemntov matrici A - musor;
 //t.k. etoy informacii budet dostatochno vvidu trexdiagonalnosti i samosopryajennosti resheniya(itogovoy pochti treugolnoy matrici)

 //1 - opredelitel != 0; 0 - opredelitel == 0
int is_det_zero_13_13(int n, double* A, double precision);

 //0 - работа завершена успешно, матрица упрощена
 //-1 - метод упрощения не применим к данной матрице
int sim_13_13(int n, double* A, double* tmp, double precision);
//videlyaet pamyat dlya parametra tmp v sim_13_13, poka xvataet n double-ov
int sim_memsize_13_13(int n);


 //0 - работа завершена успешно
 //1 - метод не сходится за указанное число итераций
 //-1 - метод поиска собственных значений не применим к данной матрице - nikogda ne vozvrashaet t.k. 
 //pri vipolnenii sim_13_13 uje proveryaetsa primenimost metoda k dannoy matrice

 //vmesto matrici A, diagonalnie elementi i elementi pervoy poddiagonali, t.k. matrica samosopryajena i pochti treugolna, 
 //t.e. simmetrichna i trexdiagonalna
 //poetomu peredayu v parametre A : diagonalnie elementi (razmer : n)
 //a v parametre B : elementi pervoy poddiagonali(razmer : n - 1)
 //tmp ostavil na budushee : vdrug ponadobitsya
int evc_13_13(int n, int max_iterations, double epsilon, double* A, double* B, double* E, double* tmp, double precision);
int evc_memsize_13_13(int n); //nichego ne videlyaet : ne nujno
int evc_13_13_2(double a11, double a12, double a21, double a22, double& e1, double& e2);//reshaet zadachu dlya matric razmera 2X2
int evc_13_13_sort(int n, double* E); //sortiruet massiv chisel

