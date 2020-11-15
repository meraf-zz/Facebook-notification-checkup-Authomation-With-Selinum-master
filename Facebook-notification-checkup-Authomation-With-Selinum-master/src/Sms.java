import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.Account;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.security.AccessControlContext;

public class Sms
{
    /* Get credential from environment*/
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");


    public Sms()
    {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void SendSms(String mess) {

        Message message = Message.creator(
                new PhoneNumber(System.getenv("MY_PHONE_NUMBER")),
                new PhoneNumber("+15092608670"), //twilio number
                "You have " + mess + " New Facebook Notifications"

        ).create();

        System.out.println(message.getSid());

    }











}
