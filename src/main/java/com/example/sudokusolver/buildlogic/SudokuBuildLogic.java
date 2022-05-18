package com.example.sudokusolver.buildlogic;

import com.example.sudokusolver.computationlogic.GameLogic;
import com.example.sudokusolver.persistence.LocalStorageImpl;
import com.example.sudokusolver.problemdomain.IStorage;
import com.example.sudokusolver.problemdomain.SudokuGame;
import com.example.sudokusolver.userinterface.IUserInterfaceContract;
import com.example.sudokusolver.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic
                = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
