package org.scrum.psd.battleship.ascii;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.scrum.psd.battleship.controller.GameController;
import org.scrum.psd.battleship.controller.dto.Letter;
import org.scrum.psd.battleship.controller.dto.Position;
import org.scrum.psd.battleship.controller.dto.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List<Ship> myFleet;
    private static List<Ship> enemyFleet;
    private static List<Position> mySuccesShots = new ArrayList<>();
    private static List<Position> myMissedShots = new ArrayList<>();
    private static List<Position> enemySuccesShots = new ArrayList<>();
    private static ColoredPrinter console;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN =  "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_MAGENTA =  "\u001B[35m";



    public static void main(String[] args) {
        console = new ColoredPrinter.Builder(1, false).build();

        console.setForegroundColor(Ansi.FColor.MAGENTA);
        console.println("                                     |__");
        console.println("                                     |\\/");
        console.println("                                     ---");
        console.println("                                     / | [");
        console.println("                              !      | |||");
        console.println("                            _/|     _/|-++'");
        console.println("                        +  +--|    |--|--|_ |-");
        console.println("                     { /|__|  |/\\__|  |--- |||__/");
        console.println("                    +---------------___[}-_===_.'____                 /\\");
        console.println("                ____`-' ||___-{]_| _[}-  |     |_[___\\==--            \\/   _");
        console.println(" __..._____--==/___]_|__|_____________________________[___\\==--____,------' .7");
        console.println("|                        Welcome to Battleship                         BB-61/");
        console.println(" \\_________________________________________________________________________|");
        console.println("");
        console.clear();

        InitializeGame();

        StartGame();
    }

    private static void StartGame() {
        Scanner scanner = new Scanner(System.in);

        console.print("\033[2J\033[;H");
        console.println("                  __");
        console.println("                 /  \\");
        console.println("           .-.  |    |");
        console.println("   *    _.-'  \\  \\__/");
        console.println("    \\.-'       \\");
        console.println("   /          _/");
        console.println("  |      _  /\" \"");
        console.println("  |     /_\'");
        console.println("   \\    \\_/");
        console.println("    \" \"\" \"\" \"\" \"");

        do {

            console.println("");
            printGameState();
            console.println("Player, it's your turn");
            console.println("Enter coordinates for your shot :");
            Position position = parsePosition(scanner.next());

            boolean isHit = GameController.checkIsHit(enemyFleet, position);
            if (isHit) {
                mySuccesShots.add(position);
                beep();

                console.println("                \\         .  ./");
                console.println("              \\      .:\" \";'.:..\" \"   /");
                console.println("                  (M^^.^~~:.'\" \").");
                console.println("            -   (/  .    . . \\ \\)  -");
                console.println("               ((| :. ~ ^  :. .|))");
                console.println("            -   (\\- |  \\ /  |  /)  -");
                console.println("                 -\\  \\     /  /-");
                console.println("                   \\  \\   /  /");
            } else {
                myMissedShots.add(position);
            }

            console.println(isHit ? "Yeah ! Nice hit !" : "Miss");

            position = getRandomPosition();
//            position = new Position(Letter.B,4);
            isHit = GameController.checkIsHit(myFleet, position);
            console.println("");
            System.out.println(ANSI_MAGENTA);
            console.println(String.format("Computer shoot in %s%s and %s", position.getColumn(), position.getRow(), isHit ? "hit your ship !" : "miss"));
            System.out.println(ANSI_RESET);
            if (isHit) {
                enemySuccesShots.add(position);
                beep();

                console.println("                \\         .  ./");
                console.println("              \\      .:\" \";'.:..\" \"   /");
                console.println("                  (M^^.^~~:.'\" \").");
                console.println("            -   (/  .    . . \\ \\)  -");
                console.println("               ((| :. ~ ^  :. .|))");
                console.println("            -   (\\- |  \\ /  |  /)  -");
                console.println("                 -\\  \\     /  /-");
                console.println("                   \\  \\   /  /");
            }
        } while (true);
    }

    private static void beep() {
        console.print("\007");
    }

    protected static Position parsePosition(String input) {
        Letter letter = null;
        int number = -1;
        try {
            letter = Letter.valueOf(input.toUpperCase().substring(0, 1));
            number = Integer.parseInt(input.substring(1));
        } catch (IllegalArgumentException e) {
            System.out.println(ANSI_RED);
            System.out.println("");
            System.out.println(ANSI_RESET);
        }

        return new Position(letter, number);
    }

    private static Position getRandomPosition() {
        int rows = 8;
        int lines = 8;
        Random random = new Random();
        Letter letter = Letter.values()[random.nextInt(lines)];
        int number = random.nextInt(rows);
        Position position = new Position(letter, number);
        return position;
    }

    private static void InitializeGame() {
        // InitializeMyFleet();
        InitializeMyStaticFleet();

        InitializeEnemyFleet();
    }

    private static void InitializeMyFleet() {
        Scanner scanner = new Scanner(System.in);
        myFleet = GameController.initializeShips();

        console.println("Please position your fleet (Game board has size from A to H and 1 to 8) :");

        for (Ship ship : myFleet) {
            console.println("");
            console.println(String.format("Please enter the positions for the %s (size: %s)", ship.getName(), ship.getSize()));
            for (int i = 1; i <= ship.getSize(); i++) {
                console.println(String.format("Enter position %s of %s (i.e A3):", i, ship.getSize()));

                String positionInput = scanner.next();
                ship.addPosition(positionInput);
            }
        }
    }

    private static void InitializeEnemyFleet() {
        enemyFleet = GameController.initializeShips();

        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(0).getPositions().add(new Position(Letter.B, 4));

        enemyFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(1).getPositions().add(new Position(Letter.B, 4));

        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(2).getPositions().add(new Position(Letter.B, 4));

        enemyFleet.get(3).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(3).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(3).getPositions().add(new Position(Letter.B, 4));

        enemyFleet.get(4).getPositions().add(new Position(Letter.B, 4));
        enemyFleet.get(4).getPositions().add(new Position(Letter.B, 4));
    }

    private static void InitializeMyStaticFleet() {
        myFleet = GameController.initializeShips();

        myFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(0).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(0).getPositions().add(new Position(Letter.B, 4));

        myFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(1).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(1).getPositions().add(new Position(Letter.B, 4));

        myFleet.get(2).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(2).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(2).getPositions().add(new Position(Letter.B, 4));

        myFleet.get(3).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(3).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(3).getPositions().add(new Position(Letter.B, 4));

        myFleet.get(4).getPositions().add(new Position(Letter.B, 4));
        myFleet.get(4).getPositions().add(new Position(Letter.B, 4));
    }

    private static void printGameState() {
        System.out.println(ANSI_GREEN + "Yours success shots: " + mySuccesShots + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Yours missed shots: " + myMissedShots + ANSI_RESET);
        System.out.println(ANSI_RED + "PC missed shots: " + enemySuccesShots + ANSI_RESET);
    }
}
