import apisdk.ApiClient;
import apisdk.models.*;
import com.microsoft.kiota.authentication.*;
import com.microsoft.kiota.http.OkHttpRequestAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Example {

    static final String openAIAPIKey = System.getenv("OPENAI_API_KEY");

    public static void main(String[] args) throws Exception {

        // var adapter = new OkHttpRequestAdapter(new AnonymousAuthenticationProvider());

        // https://platform.openai.com/docs/api-reference/authentication
        var adapter = new OkHttpRequestAdapter(new BaseBearerTokenAuthenticationProvider(
                new AccessTokenProvider() {
                    @Override
                    public CompletableFuture<String> getAuthorizationToken(@NotNull URI uri, @Nullable Map<String, Object> additionalAuthenticationContext) {
                        return CompletableFuture.completedFuture(openAIAPIKey);
                    }
                    @Override
                    public AllowedHostsValidator getAllowedHostsValidator() {
                        return new AllowedHostsValidator();
                    }
                }
        ));
        var client = new ApiClient(adapter);

        // APIGuru
//        var providers = client.providersJson().get().get().getData();
//        var apicurioProvider = providers.stream().filter(p -> p.contains("apicurio")).findFirst().get();
//        var services = client.byProvider(apicurioProvider).servicesJson().get().get();
//
//        assert (services.getData().get(0).equals("registry"));
//        System.out.println("Yes! Apicurio Provider " + apicurioProvider + " does have REGISTRY!");

        // OpenAI
        // https://platform.openai.com/docs/api-reference/making-requests
        //
        var req = new CreateChatCompletionRequest();
        var completion = new CreateChatCompletionRequest.Completions();
        completion.setCompletionsString("gpt-3.5-turbo");
        req.setModel(completion);
        req.setMaxTokens(100);
        var message = new ChatCompletionRequestMessage();

        message.setRole(ChatCompletionRequestMessageRole.User);
        message.setContent("Say Hi to the Milan's JUG!!!");
        req.setMessages(List.of(message));

        var resp = client.chat().completions().post(req).get();

        System.out.println(resp.getChoices().get(0).getMessage().getContent());

        System.exit(0);
    }
}
