
//procedura vichislyayushaya koefficienti interpoliruyushey funkcii
//kolichestvo uzlov v kotorix izvestno znachenie funkcii
//tochki v kotorix izvestno znachenie funkcii
//znachenie funkcii v tochkax iz x
//koef rezultat raboti procedure calcKoef : 4 koefficienta dlya mnogochlena Pi(i=1..n-1) stepeni 3
//vsya pamyat dlya parametrov videlyaetsa i unichtojaetsa snaruji

int calcKoef(int n, double* x, double* f, double* koef);

//0 esli zaversheno uspeshno


//procedura vichislyayushaya znachenie funkcii v k+1 tochkax
//Pi(x)=c1i+c2i(x-xi)+c3i(x-xi)^2+c4i(x-xi)^3
//koef - c1i, 2i, c3i, c4i(i=1..n-1)
//a, b - granici interpolyacii
//k kolichestvo tochek v kotorix nujno vichislit znacheniya priblijayushey funkcii
//x pervonachalno zadannie tochki v kotorix izvestno znachenie funkcii : sootvetstvuyut parametru x v calcKoef
//val - rezultat raboti proceduri
//n - kolichestvo tochek vo vxodnom fayle
int calcFunc(double* koef, double a, double b, int k, double* x, double* val, int n);
//vozvrashaet -1 pri slishkom bolshom k
//vozvrashaet 0 esli vse norm zavershilos