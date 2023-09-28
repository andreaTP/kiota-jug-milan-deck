import apisdk.ApiClient;
import com.microsoft.kiota.authentication.AnonymousAuthenticationProvider;
import com.microsoft.kiota.http.OkHttpRequestAdapter;

public class APIGuruExample {

    public static void main(String[] args) throws Exception {

        var adapter = new OkHttpRequestAdapter(new AnonymousAuthenticationProvider());
        var client = new ApiClient(adapter);

        var providers = client.providersJson().get().get().getData();
        var apicurioProvider = providers.stream().filter(p -> p.contains("apicurio")).findFirst().get();
        var services = client.byProvider(apicurioProvider).servicesJson().get().get();

        assert (services.getData().get(0).equals("registry"));
        System.out.println("Yes! Apicurio Provider " + apicurioProvider + " does have REGISTRY!");

        System.exit(0);
    }
}
