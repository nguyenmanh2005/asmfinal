import java.util.Random;

public class StudentStack {
    Stack<Student> studentStack;

    public StudentStack() {
        studentStack = new Stack<>();
    }

    public void pushStudent(Student student) {
        studentStack.push(student);
    }

    public Student popStudent() {
        return studentStack.pop();
    }

    public void generateRandomStudents(int count) {
        for (int i = 0; i < count; i++) {
            String id = "S" + (10000 + i);  // Use i to generate unique IDs
            String name = "Nguyễn Tuấn Mạnh";  // Fixed name
            double marks = Math.round((1 + Math.random() * 9) * 10.0) / 10.0;  // Random marks
            pushStudent(new Student(id, name, marks));
        }
    }


    public boolean addStudent(Student student) {
        Stack<Student> tempStack = new Stack<>();
        boolean studentExists = false;

        while (!studentStack.isEmpty()) {
            Student existingStudent = studentStack.pop();
            if (existingStudent.getId().equals(student.getId())) {
                studentExists = true;
            }
            tempStack.push(existingStudent);
        }

        if (!studentExists) {
            studentStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        return !studentExists;
    }

    public boolean deleteStudent(String id) {
        Stack<Student> tempStack = new Stack<>();
        boolean studentFound = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                studentFound = true;
                break;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        return studentFound;
    }

    public Student searchStudent(String id) {
        Stack<Student> tempStack = new Stack<>();
        Student foundStudent = null;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                foundStudent = student;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        return foundStudent;
    }

    public boolean editStudent(String id, String newName, double newMarks) {
        Stack<Student> tempStack = new Stack<>();
        boolean studentFound = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                student.setName(newName);
                student.setMarks(newMarks);
                studentFound = true;
            }
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }

        return studentFound;
    }

    public Stack<Student> cloneStack() {
        Stack<Student> tempStack = new Stack<>();
        Stack<Student> clonedStack = new Stack<>();

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            Student student = tempStack.pop();
            studentStack.push(student);
            clonedStack.push(student);
        }

        return clonedStack;
    }

    public void quickSort(Stack<Student> stack) {
        if (stack.size() <= 1) return;

        Random random = new Random();
        int randomIndex = random.nextInt(stack.size());
        Stack<Student> tempStack = new Stack<>();

        Student pivot = null;
        for (int i = 0; i <= randomIndex; i++) {
            pivot = stack.pop();
            tempStack.push(pivot);
        }

        Stack<Student> left = new Stack<>();
        Stack<Student> right = new Stack<>();

        while (!stack.isEmpty()) {
            Student student = stack.pop();
            if (student.getMarks() <= pivot.getMarks()) {
                left.push(student);
            } else {
                right.push(student);
            }
        }

        quickSort(left);
        quickSort(right);

        while (!right.isEmpty()) stack.push(right.pop());
        stack.push(pivot);
        while (!left.isEmpty()) stack.push(left.pop());
        while (!tempStack.isEmpty()) stack.push(tempStack.pop());
    }

    public void bubbleSort(Stack<Student> stack) {
        Student[] students = stack.toArray(new Student[0]);
        int n = students.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        stack.clear();
        for (Student student : students) {
            stack.push(student);
        }
    }


    public void mergeSort(Stack<Student> stack) {
        if (stack.size() <= 1) return;

        Stack<Student> left = new Stack<>();
        Stack<Student> right = new Stack<>();

        int mid = stack.size() / 2;
        for (int i = 0; i < mid; i++) {
            left.push(stack.pop());
        }

        while (!stack.isEmpty()) {
            right.push(stack.pop());
        }


        mergeSort(left);
        mergeSort(right);


        merge(left, right, stack);
    }

    private void merge(Stack<Student> left, Stack<Student> right, Stack<Student> result) {

        Stack<Student> temp = new Stack<>();


        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.peek().getMarks() <= right.peek().getMarks()) {
                temp.push(left.pop());
            } else {
                temp.push(right.pop());
            }
        }


        while (!left.isEmpty()) {
            temp.push(left.pop());
        }

        while (!right.isEmpty()) {
            temp.push(right.pop());
        }


        while (!temp.isEmpty()) {
            result.push(temp.pop());
        }
    }

    public void displayStudents() {
        Stack<Student> tempStack = new Stack<>();

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            System.out.println(student);
            tempStack.push(student);
        }

        while (!tempStack.isEmpty()) {
            studentStack.push(tempStack.pop());
        }
    }
}
