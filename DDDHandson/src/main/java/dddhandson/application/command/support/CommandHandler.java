package dddhandson.application.command.support;

/**
 * Interfaccia per i gestori di comandi.
 *
 * @param <C>
 *            tipo del comando gestito
 */
public interface CommandHandler<C> {

    /**
     * @param command
     *            da gestire
     * @return risultato
     * @throws Exception
     *             in caso di errori
     */
    public Object handle(C command) throws Exception;

}
