package br.com.alanpcavalcante.araraflyapi.application.gateways.notification;

import br.com.alanpcavalcante.araraflyapi.domain.user.Email;

public interface Notification {

    void send(Email to, TextField subject, TextField htmlContent);

}
