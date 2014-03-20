package dddhandson.application.command.support;

import dddhandson.domain.support.Command;

/**
 * Interfaccia per i gestori di comandi.
 *
 * @param <C>
 *            tipo del comando gestito
 */
public interface CommandHandler<C extends Command> {

    /**
     * @param command
     *            da gestire
     * @return risultato
     * @throws Exception
     *             in caso di errori
     */
    public void handle(C command) throws Exception;

}
