#include <stdio.h>
#include <omp.h>

int main(){
    omp_set_num_threads(16);
    #pragma omp parallel num_threads(8)
    {
        printf("Hello world!!!\n"); // Execute in parallel
    }
    return 0;
}