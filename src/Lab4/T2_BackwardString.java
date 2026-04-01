package Lab4;

// 2. Напишите рекурсивный метод отображающий строку задом на перед.
public class T2_BackwardString {
    /// id must be the size of input
    public void Solve(String input){
        Solve(input, input.length()-1);
    }

    private void Solve(String input, int id) {
        if(id == -1) {
            IO.println();
            return;
        }
        else {
            IO.print(input.charAt(id));
            Solve(input, --id);
        }
    }
}
