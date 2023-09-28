import asyncio
from kiota_http.httpx_request_adapter import HttpxRequestAdapter
from kiota_abstractions.authentication.anonymous_authentication_provider import AnonymousAuthenticationProvider
from client.api_client import ApiClient

async def main():
    request_adapter = HttpxRequestAdapter(AnonymousAuthenticationProvider())
    client = ApiClient(request_adapter)
    
    commits = await client.repos.by_org("andreaTP").by_repo("std-uritemplate").commits.get()
    
    for commit in commits:
      if (commit.author.login == "andreaTP"):
        print(f"This is my commit [{commit.sha}]")
      else:
        print(f"skipping commit by {commit.author.login}")

# Run main
asyncio.run(main())