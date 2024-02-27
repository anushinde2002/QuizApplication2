
import java.util.*;

class Question {
    private String question;

    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class QuizApplication {
    private static final int QUIZ_TIME_LIMIT_SECONDS = 60;
    private static final int NUM_QUESTIONS = 5;

    private List<Question> questions;
    private int score;
    private Timer timer;

    public QuizApplication() {
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        // Add your questions here
        // Each question should have a question statement, options, and correct option
        // index
        questions.add(new Question("What is the capital of France?",
                Arrays.asList("London", "Paris", "Berlin", "Rome"), 1));
        questions.add(new Question("What is 2 + 2?",
                Arrays.asList("3", "4", "5", "6"), 1));
        // Add more questions as needed
    }

    public void startQuiz() {
        score = 0;

        System.out.println("Welcome to the Quiz! You have " + QUIZ_TIME_LIMIT_SECONDS + " seconds to complete.");

        TimerTask task = new TimerTask() {
            int seconds = QUIZ_TIME_LIMIT_SECONDS;

            public void run() {
                if (seconds <= 0) {
                    timer.cancel();
                    showResult();
                    return;
                }
                System.out.println("Time left: " + seconds + " seconds");
                seconds--;
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, 50, 5000);

        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            List<String> options = question.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.print("Enter your answer: ");
            int userAnswer = scanner.nextInt();
            if (userAnswer - 1 == question.getCorrectOptionIndex()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        showResult();
        scanner.close();
    }

    private void showResult() {
        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + " out of " + NUM_QUESTIONS);
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}
