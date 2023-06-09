import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label bestScoreLabel;
    @FXML
    private Label timerLabel;

    private int score;
    private int bestScore;
    private int timeLeft;
    private Random random;
    private Timeline gameTimeline;
    private Timeline timerTimeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        random = new Random();
        bestScore = 0;
        updateScoreLabel();
        updateBestScoreLabel();

        button.setOnAction(this::handleButtonClick);
        startGame();
    }

    private void startGame() {
        score = 0;
        updateScoreLabel();
        button.setLayoutX(random.nextDouble() * 400);
        button.setLayoutY(random.nextDouble() * 400);
        button.setDisable(false);

        if (gameTimeline != null) {
            gameTimeline.stop();
        }

        if (timerTimeline != null) {
            timerTimeline.stop();
        }

        timeLeft = 15;
        updateTimerLabel();

        gameTimeline = new Timeline(new KeyFrame(Duration.seconds(15), this::handleGameEnd));
        gameTimeline.play();

        timerTimeline = new Timeline(new KeyFrame(Duration.seconds(1), this::updateTimer));
        timerTimeline.setCycleCount(15);
        timerTimeline.play();
    }

    private void handleButtonClick(ActionEvent event) {
        score++;
        updateScoreLabel();

        if (score > bestScore) {
            bestScore = score;
            updateBestScoreLabel();
        }

        double newX = random.nextDouble() * 400;
        double newY = random.nextDouble() * 400;
        button.setLayoutX(newX);
        button.setLayoutY(newY);
        button.setDisable(false);
    }

    private void handleGameEnd(ActionEvent event) {
        button.setDisable(true);
        gameTimeline.stop();
        timerTimeline.stop();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    private void updateBestScoreLabel() {
        bestScoreLabel.setText("Best Score: " + bestScore);
    }

    private void updateTimer(ActionEvent event) {
        timeLeft--;
        updateTimerLabel();

        if (timeLeft == 0) {
            timerTimeline.stop();
        }
    }

    private void updateTimerLabel() {
        timerLabel.setText("Time Left: " + timeLeft + " sec");
    }
    @FXML
    private void startNewGame(ActionEvent event) {
        startGame();
    }
}
