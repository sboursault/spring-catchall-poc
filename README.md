# gs-springboot-rest-mongo-docker


Demo for a rest api representing the Arkham Asylum.

featuring:
* a gradle multi-module project
* a rest api with
  * error handling,
  * forward compatible design (see Inmate.aka),
  * handling date fields
  * service discovery with HATEOAS (handle links prev, next, first, last, self, etc.)
  * a swagger-ui client,
  * a spring rest docs documentation
  * internal and external representations defined in separate classes, mapping realized by mapstruct 
* a mongo repository
* integration tests based on spring's mockMvc and an embedded mongodb
* a docker-composed development environment
* simple analytics with redis
* the elk stack to browse logs (logstash, elastic-search, kibana)
* Async processing for analytics
* tracing with correlation to aggregate the logs for a single transaction
* service discovery with zookeeper
* client side load balancing with ribbon and spring-retry
* async messages with rabbitMQ

Yet to come: 
* monitoring with grafana to inspect behaviors under load tests 
* uri and model versionning
* improved hateoas discoverability
* decouple storage representation from the one exposed
* access restrictions
* project versionning and packaging (should include a snapshot dependency)
* handle PATCH and PUT operations, collection with sort and pagination
* data import with spring integration
* https dev environment (https://hub.docker.com/r/marvambass/nginx-ssl-secure/)
* elastic search index
* load balancing, shared session storage
* blue/greeen deployment
* discover the api through the browser
* add a sort of task engine to rerun failed tasks (spring integration ?)
* check visual non regression like this: https://medium.com/friday-people/how-we-do-visual-regression-testing-af63fa8b8eb1
* dynamic configuration with zookeeper
* async strategies
  * back pressure (when the broker is full, sync message, when the consumer is full, async message)
  * poison message
 

Resource:
https://spring.io/guides/tutorials/bookmarks/
https://spring.io/guides
https://en.wikipedia.org/wiki/List_of_Batman_Family_adversaries
