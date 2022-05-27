
- Not all endpoints are implemented; Rather that implementing the same thing 3 times, I chose to focus more on supporting pagination and tests.
- The cucumber tests are not "production ready"; 
- I didn't manage to solve one issue in a timely manner: the actual external api gets called when running bdds. 
My intention was to setup a wireMock server to handle the feign client's request, but I didn't managed to make it work with the cucumber test runner.
Once the test profile (to use the stubbed url) is activated, the cucumber tests won't run anymore. I will need to read a bit more on the way cucumber test runner works.
Second, I tried to mock the feign client but that didn't work well either with the cucumber test runner;
There are other ways to explore also, but as I said, I would need more time;
