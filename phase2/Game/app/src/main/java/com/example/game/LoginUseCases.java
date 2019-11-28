package com.example.game;

import android.content.Context;

import com.example.game.models.AccountManagerInterface;
import com.example.game.models.LoginListener;

/**
 * Class which performs the use cases for logging in with accounts.
 */
class LoginUseCases {
    private AccountManagerInterface accountManager;

    LoginUseCases(AccountManagerInterface accountManager){
        this.accountManager = accountManager;
    }

    /**
     * Determines whether a username exists or does not exist (correct or incorrect).
     * @param username is the entered in username for an attempted login
     * @param context the location of the application
     * @param loginReactor will react to whatever result is concluded from the return of
     *                     accountManager
     */
    void login(final String username, Context context,
                      LoginListener loginReactor){
        Account account = accountManager.openExistingAccount(username, context);
        if(account != null){
            loginReactor.correctUsername(account);
        } else {
            loginReactor.incorrectUsername();
        }
    }
}