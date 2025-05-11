# Brief
- Engineered a highly concurrent and available message distribution management platform using the Spring
Cloud framework, capable of handling message delivery to millions of users. The platform provides end-to-end
management of delivery statuses, message content, and system logs.
- Used a microservices architecture, the system’s module coupling was significantly reduced. Kafka message queues were
used to further enhance system concurrency and enable asynchronous processing. Additionally, the Apollo distributed
configuration center was implemented for efficient configuration management.

# Project structure
```angular2html
notification-distributed-platform/
├── ndp-common/
│   ├── src/
│   └── pom.xml
├── ndp-handler/
│   ├── src/
│   └── pom.xml
├── ndp-serviceapi/
│   ├── src/
│   └── pom.xml
├── ndp-serviceapi-impl/
│   ├── src/
│   └── pom.xml
├── ndp-support/
│   ├── src/
│   └── pom.xml
└── pom.xml
```
## Module Description
- ndp-common: Contains shared utilities and common functionalities used across other modules.
- ndp-handler: Responsible for processing and handling notification events.
- ndp-serviceapi: Defines the service interfaces for notification operations.
- ndp-serviceapi-impl: Provides concrete implementations of the service APIs.
- ndp-support: Includes supporting tools and configurations to aid in the platform's operation.