import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameController {

    @FXML
    private Button button;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label highScoreLabel;

    @FXML
    private Button newGameButton;

    private int score;
    private int highScore;

    private Timeline timeline;

    public void initialize() {
        score = 0;
        highScore = 0;
        scoreLabel.setText("Score: 0");
        highScoreLabel.setText("High Score: 0");

        button.setOnAction(event -> {
            increaseScore();
            updateScoreLabel();
            increaseButtonSpeed();
        });

        newGameButton.setOnAction(event -> startNewGame());

        startNewGame();
    }
    @FXML
    private void startNewGame() {
        score = 0;
        scoreLabel.setText("Score: 0");
        hideNewGameButton();
        startGame();
    }

    private void startGame() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> moveButton()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopGame() {
        timeline.stop();
    }

    private void moveButton() {
        double newX = Math.random() * 450;
        double newY = Math.random() * 450;
        button.setLayoutX(newX);
        button.setLayoutY(newY);
    }

    private void increaseScore() {
        score++;
        if (score > highScore) {
            highScore = score;
            highScoreLabel.setText("High Score: " + highScore);
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    private void increaseButtonSpeed() {
        timeline.setRate(timeline.getRate() + 0.1);
    }

    private void showNewGameButton() {
        newGameButton.setVisible(true);
    }

    private void hideNewGameButton() {
        newGameButton.setVisible(false);
    }
}
