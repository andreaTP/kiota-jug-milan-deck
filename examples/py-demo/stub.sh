echo "import asyncio
from kiota_http.httpx_request_adapter import HttpxRequestAdapter
from kiota_abstractions.authentication.anonymous_authentication_provider import AnonymousAuthenticationProvider
from client.api_client import ApiClient

async def main():
    print(\"start\")

# Run main
asyncio.run(main())" > main.py