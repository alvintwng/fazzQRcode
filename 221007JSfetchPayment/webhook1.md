### Faxx Callback using Webhook

Using Postmanm or Command Prompt

browser: https://webhook.site

Retrive all json data
``` console
C:\Users\AlvinNg>curl https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f/requests
{"data":[{"uuid":"5a717ea1-8d5b-40a1-a6dd-18cdaa69c0bd","type":"web","token_id":"8b232988-c29e-4038-8c ...
...
output":[]}],"total":4,"per_page":50,"current_page":1,"is_last_page":true,"from":1,"to":4}
```

Token Detail
``` console
C:\Users\AlvinNg>curl  https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f
{"uuid":"8b232988-c29e-4038-8c57-fd13f943822f","redirect":false,"alias":null,"actions":false,"cors" 
...
,"latest_request_id":"4d0c5971-7934-42e9-9b71-a7dc7bf74901","latest_request_at":"2022-09-30 03:36:25"}
```

Retreat the latest:
``` console
C:\Users\AlvinNg>curl https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f/request/latest
{"uuid":"4d0c5971-7934-42e9-9b71-a7dc7bf74901","type":"web","token_id":"8b232988-c29e-4038-8c57-fd13f94382
... 
"sorting":1664508985804650,"custom_action_output":[]}
```

Single Retreat Latest Raw
``` console
C:\Users\AlvinNg>curl https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f/request/latest/raw
{"data":{"id":"contract_7d7e6e82d52e46e0a732b6de35175990","type":"payment","attributes":{"status":"completed",
...
"bankShortCode":"CIMB","accountNo":"7705090533450"}}}}}
```

Single Retreat Raw via Uuid
``` console
C:\Users\AlvinNg>curl https://webhook.site/token/8b232988-c29e-4038-8c57-fd13f943822f/request/7e7bfd1e-b286-4750-93ac-5ad3d39f8296/raw
{"data":{"id":"contract_ef59605baad04e1b8616a6aa912da914","type":"payment","attributes":{"status":"paid",
...
CgwiIiIjwKDiIj4KDCIiIiPAoOIiPj8fy3ppPUpqsjuAAAAAElFTkSuQmCC"}}}}}
C:\Users\AlvinNg>
```

```
