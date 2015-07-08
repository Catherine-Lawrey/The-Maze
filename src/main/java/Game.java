import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by catherine on 05/07/15.
 */
public class Game {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Person p = new Person(0, 5);
        Maze maze = new Maze(
                "┏━━━━━━━━┳━━━━━━━━━┳━━┳━━━━┳━┓",
                "┃........┃.........┃..┃....┃.┃",
                "┃.━━━┓..━┫..┏━━━━━....┃..┃...┃",
                "┃....┃...┃..┃......┏━━┛..┃.┃.┃",
                "┣━.━━┻┓..┗━.┃.┃.┃..┃.....┃...┃",
                "┃.....┃.....┃...┃..┃.┏━━━┻━┓ ┃",
                "┃.┏━┓.┃.┏━━━┛.┃.┃....┃.....┣.┫",
                "┃.┃.....┃.....┗━┻.┏━┳┻━━.┃...┃",
                "┃.┃.┗━━━┛.┃.......┃.┃....┣━┓.┃",
                "O.┃.......┣━━━━━.━┫....┃...┣━┫",
                "┃.┃.┏━.┏━┛........┃.┃..┃.┃.┃.X",
                "┃.┃.┃..┃....━━┓.┏━┫.┃..┃.┃...┃",
                "┃...┣━.┛..┃...┃.┃.┃....┃...┃.┃",
                "┃...┃....━┫.......┃....┃.┃.┃.┃",
                "┗━━━┻━━━━━┻━━━┻━━━┻━━━━┻━┻━┻━┛"
        );
        System.out.println("Welcome to the maze. ");
        while (maze.at(p.x, p.y) != 'X') {
            System.out.println();

            // This prints the map.
            for (int j = 2; j >= -2; j--) {
                for (int i = -2; i <= 2; i++) {
                    if (i == 0 && j == 0)
                        System.out.print("☺");
                    else
                        System.out.print(maze.at(p.x + i, p.y + j));
                }
                System.out.println();
            }

            // This prints where you can go.
            List<String> dir = new ArrayList<>();
            if (!maze.isWall(p.x - 1, p.y)) dir.add("(w)est");
            if (!maze.isWall(p.x + 1, p.y)) dir.add("(e)ast");
            if (!maze.isWall(p.x, p.y + 1)) dir.add("(n)north");
            if (!maze.isWall(p.x, p.y - 1)) dir.add("(s)outh");

            System.out.println("you can go " + dir + " x: " + p.x + " y: " + p.y);

            System.out.println("Where would you like to go?");
            String ans = in.nextLine().toLowerCase();
            switch (ans) {
                case "w":
                case "west":
                    if (!maze.isWall(p.x - 1, p.y))
                        p.x--;
                    else
                        System.out.println("You can't go west");
                    break;

                case "e":
                case "east":
                    if (!maze.isWall(p.x + 1, p.y))
                        p.x++;
                    else
                        System.out.println("That's too scary !!!");
                    break;

                case "n":
                case "north":
                    if (!maze.isWall(p.x, p.y + 1))
                        p.y++;
                    else
                        System.out.println("no can do");
                    break;

                case "s":
                case "south":
                    if (!maze.isWall(p.x, p.y - 1))
                        p.y--;
                    else
                        System.out.println("Try again");
                    break;

                default:
                    // ???
                    System.out.println("I don't know how to " + (ans));
            }
        }
        System.out.println("Good choice, you escape the maze");
    }
}

class Person {
    int x, y;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Maze {
    String[] layout;

    Maze(String... layout) {
        this.layout = layout;
    }

    public char at(int x, int y) {
        if (y < 0 || y >= layout.length || x < 0 || x >= layout[layout.length - 1 - y].length())
            return '#';
        return layout[layout.length - 1 - y].charAt(x);
    }

    public boolean isWall(int x, int y) {
        return (at(x, y) & 0x2580) == 0x2500;
    }
}
