package pl.monopoly.view;

import pl.monopoly.logic.Cubes;
import pl.monopoly.logic.Game;
import pl.monopoly.logic.Player;
import pl.monopoly.logic.SettingsState;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gameplay {
    private final Game game;
    private final PlayerView[] playerViews; = {new PlayerView(player1, player2, player3, player4), new PlayerView(player2, player1, player3, player4), new PlayerView(player3, player1, player2, player4), new PlayerView(player4, player1, player2, player3)};
    private final CubesView cubesView = new CubesView(new Cubes(game));
    private static Graphics graphics;
    protected static int colorIndex = 0;

    // create
    public Gameplay(MouseManager manager) {

        // private final Player player1 = new Player(), player2 = new Player(), player3 = new Player(), player4 = new Player();
        List<Player> players = new ArrayList<>();
        List<PlayerView> playerViewList = new ArrayList<>();
        for (int i = 0; i < SettingsState.getInstance().getPlayersNumber(); i++) {
            players.add(new Player());
            playerViewList.add(new PlayerView())
        }

        game = new Game(players);
        manager.setCubesView(cubesView);

    }

    public void tick() {

//        System.out.println("start");

    }

    public void render(Graphics g) {
        graphics = g;
        setBackgroundColor(colorIndex);
        graphics.fillRect(0,0,Display.getWidth(),Display.getHeight());
        Gameplay.graphics.translate(Display.getRelativeX(),Display.getRelativeY());
        Image image = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\board.png");
        Gameplay.graphics.drawImage(image, 0,0, null);
        cubesView.render(Gameplay.graphics);

        switch (Game.playersNumber) {
            case 2 -> {
                playerViews[0].render(Gameplay.graphics);
                playerViews[1].render(Gameplay.graphics);
            }
            case 3 -> {
                playerViews[0].render(Gameplay.graphics);
                playerViews[1].render(Gameplay.graphics);
                playerViews[2].render(Gameplay.graphics);
            }
            case 4 -> {
                playerViews[0].render(Gameplay.graphics);
                playerViews[1].render(Gameplay.graphics);
                playerViews[2].render(Gameplay.graphics);
                playerViews[3].render(Gameplay.graphics);
            }
        }
    }

    public static void setBackgroundColor(int colorIndex) {

        switch (colorIndex) {
            case 0 -> graphics.setColor(new Color(102, 255, 102));
            case 1 -> graphics.setColor(new Color(51, 153, 255));
            case 2 -> graphics.setColor(new Color(255, 102, 102));
            case 3 -> graphics.setColor(new Color(255, 255, 0));
        }

        graphics.fillRect(0,0,Display.getWidth(),Display.getHeight());

    }

    public static void playSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        File musicFile = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public Player getPlayer4() {
        return player4;
    }
}
