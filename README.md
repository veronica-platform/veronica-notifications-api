# Veronica Notifications Microservice

```json
{
  "channel": "#general",
  "blocks": [
    {
      "type": "header",
      "text": {
        "type": "plain_text",
        "text": "New request"
      }
    },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*Type:*\nPaid Time Off"
        },
        {
          "type": "mrkdwn",
          "text": "*Created by:*\n<example.com|Fred Enriquez>"
        }
      ]
    },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*When:*\nAug 10 - Aug 13"
        },
        {
          "type": "mrkdwn",
          "text": "*Type:*\nPaid time off"
        }
      ]
    },
    {
      "type": "section",
      "fields": [
        {
          "type": "mrkdwn",
          "text": "*Hours:*\n16.0 (2 days)"
        },
        {
          "type": "mrkdwn",
          "text": "*Remaining balance:*\n32.0 hours (4 days)"
        }
      ]
    },
    {
      "type": "section",
      "text": {
        "type": "mrkdwn",
        "text": "<https://example.com|View request>"
      }
    }
  ]
}
```