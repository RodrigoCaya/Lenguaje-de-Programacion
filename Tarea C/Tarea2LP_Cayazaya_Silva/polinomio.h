/*polinomio.h*/

typedef struct{
    float coeficiente[500];
}polinomio;

int evalpol(polinomio* poli,int n);

void integrar(polinomio* poli);

void derivar(polinomio* poli);

void* accion(void (*deit)(polinomio*),polinomio* poli);
