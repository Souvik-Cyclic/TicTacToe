package org.example;

import org.example.controllers.GameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        
        System.out.println("Welcome to Tic-Tac-Toe !");
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        String name;
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        int dimension = 3;
        List<Player> players = List.of(
            new Player(name, new Symbol('X'), PlayerType.HUMAN),
            new Bot("Computer", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY)
            );
            
            Game game = gameController.startGame(dimension, players);
            //gameController.printBoard(game);
            
        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            //1. print the board.
            gameController.printBoard(game);
            
            //2. Player's turn
            gameController.makeMove(game);
        }
        
        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
    
}