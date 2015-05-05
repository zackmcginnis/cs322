#include <stdio.h>
#include <stdlib.h>

#define X unsigned
#define P printf
#define g(x) P("%c",*(x)+48)

X n(X x){return 1664525*x+1013904223;}
X d(X x){return x%10;}X*m(X l,X s){X i
=l+3;X*r=malloc(4*i);for(;i>0;s=n(s)){
r[--i]=d(s);}r[1]=l;return r+1;} X*y(X
s,X l){X*r=malloc(4*(l+3));X*p=r+1;*r=
6;for(*p=l;l>0;l--){*++p=d(s);s/=10;}*
++p=2;return r+1;}void p(X*x){X l=1+*x
;g(x-1);P("%d",l);for(;l>0;l--){g(++x)
;}}extern X f(X*,X*);void t(X n,X*v,X*
u){P("%d:",n);p(u);p(v);P("%d",f(u,v))
;P("%d",f(v,u));p(v);p(u);P("\n");}int
main(int c,char**v){P("%s:%d\n",*v,PSU
);X x=0; X u=x;X z=PSU%91;X q=0;X*a=y(
z,0);X*b=m(23,PSU);t(q++,a,a);t(q++,a,
b);t(q++,b,a);t(q++,b,b);X n[]={15,45,
2050};for(x=0;x<3;x++){for(u=1;u<(18/(
x+1));u++){X*c=m(n[x],PSU-u);X*d=y(PSU
,n[x]);X*e=m(n[x],PSU+u);t(q++,a,c);t(
q++,b,d);t(q++,e,b);t(q++,c,d);t(q++,d
,e);t(q++,e,c);}}return 0;}/*xyzzy12*/
