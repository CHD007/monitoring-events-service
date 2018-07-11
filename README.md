# monitoring-events-service

A sandbox - self-contained testing environment that mimics the live TestPay production environment.
OAuth2 is used for calls authorization. 


// get access token

curl -v http://localhost:8080/oauth2/token -H "Accept: application/json" -H "Accept-Language: en_US" -u "user:secret"  -d "grant_type=client_credentials"   

// payment

curl -v http://localhost:8080/payments/payment -H "Content-Type: application/json" -H "Authorization: Bearer <Access-Token>" -d '{"intent": "order","notification_url": "https://example.com/your_notification_url","payer": {"email": "test@example.com"},"transaction": {"external_id": "123456789","amount": {"value": "7.47","currency": "USD"},"description": "The payment transaction description"}}'
{
          "intent": "order",
           "notification_url": "https://example.com/your_notification_url",
            "payer": {
                 "email": "test@example.com"
              },
             "transaction":{
                  "external_id": "123456789",
                  "amount":{
                       "value":"7.47",
                       "currency": "USD"
                  },
                  "description": "The payment transaction description"
             }
}
