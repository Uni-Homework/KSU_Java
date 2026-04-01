import Lab4.*;

void main() {
    IO.println("Solving Lightning");
    new T1_Lightning().Solve();

    IO.println("Solving BackwardString");
    new T2_BackwardString().Solve(IO.readln());

    IO.println("Solving Marathon");
    new T3_Marathon().Solve();

    IO.println("Solving Palindrome");
    IO.println(new T4_Palindrome().Solve(IO.readln()));

    IO.println("Solving Time");
    new T5_Time().Solve(Integer.parseInt(IO.readln()));
}
