# Facebook-notification-checkup-Authomation-With-Selinum


Description:
     
     simple script to authomate facebook login and notification checkup using java.
     the count of new notification will be send by sms, using Twilio library

Usage:

     1. Create Twilio Account to get twili0 creditntional
            - twilio phone number
            - ACCOUNT_SID
            - AUTH_TOKEN

            https://www.twilio.com/

     2. open terminal and export twilio credtial as a command varialbe

        type

            exports TWILIO_ACCOUNT_SID="youre account sid"
            exports AUTH_TOKEN="youre auth token"
            export MY_PHONE_NUMBER="youre phone number"

     3. compile and run

         type
             javac FacebookAuthomation.java Sms.java

             java FacebookAuthomation youre_email youre_password


enjoy!!!
