#include <iostream>
#include <thread>

int a = 1;

void run() { a += 1; }


int main(int argc, char** argv) {
    std::thread thread1(run);
    std::thread thread2(run);
    thread1.join();
    thread2.join();
    printf("%d\n", a);
    return 0;
}

