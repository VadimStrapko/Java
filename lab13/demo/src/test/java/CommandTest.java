import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.server.command.commands.CommandFactory;
import org.example.server.command.commands.CommandResult;
import org.example.server.command.commands.LoginCommand;
import org.example.server.command.commands.SignOutCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class CommandTest {
    @Test
    public void testFactory() {
        Assertions.assertEquals(CommandFactory.create("login").getClass().getName(), LoginCommand.class.getName());
        Assertions.assertEquals(CommandFactory.create("sign_out").getClass().getName(), SignOutCommand.class.getName());
    }

    @Test
    public void testLogin() {
        HttpServletRequest mockReq;
        mockReq = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse mockResp;
        mockResp = Mockito.mock(HttpServletResponse.class);
        Mockito.when(mockReq.getParameter("login")).thenReturn("noSuchUser");
        Mockito.when(mockReq.getSession()).thenReturn(null);
        Mockito.when(mockReq.getParameter("password")).thenReturn("noSuchPassword");
        LoginCommand loginCommand = new LoginCommand();
        CommandResult commandResult = loginCommand.execute(mockReq, mockResp);
        ArgumentCaptor<String> arg1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> arg2 = ArgumentCaptor.forClass(String.class);
        verify(mockReq).setAttribute(arg1.capture(), arg2.capture());
        System.out.println(arg1.getValue());
        System.out.println(arg2.getValue());
        Assertions.assertEquals(arg2.getValue(), "не найден пользователь");
    }
}
