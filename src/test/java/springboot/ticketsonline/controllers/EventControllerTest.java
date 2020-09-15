package springboot.ticketsonline.controllers;

/**
 * Perform test here using MockWebMvc for a change :
 * https://dimitr.im/testing-your-rest-controllers-and-clients-with-spring
 *
 * https://dimitr.im/testing-your-rest-controllers-and-clients-with-spring
 *
 *
 * https://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-rest-api/
 */
public class EventControllerTest
{
}

/*
        public GreetingHandler(WebClient webClient) {
                this.webClient = webClient;
        }

        public Mono<String> getGreetingStringMono() {
                return webClient.get().uri("http://greeting").exchange().flatMap(resp -> resp.bodyToMono(String.class))
                                .doOnSuccess(greeting -> LOG.log(Level.INFO, "Greeting: " + greeting));
        }

        public Mono<String> getGreetingStringMono(String locale) {
                LOG.log(Level.INFO, "Locale: " + locale);

                return webClient.get().uri("http://greeting/" + locale).exchange().flatMap(resp -> resp.bodyToMono(String.class))
                                .doOnSuccess(greeting -> LOG.log(Level.INFO, "Greeting: " +greeting));
        }
*/