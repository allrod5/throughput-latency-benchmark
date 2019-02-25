import asyncio

import aiohttp
from aiohttp import web


async def fetch(session, url):
    async with session.get(url) as response:
        return await response.text()


async def request_dependency(session):
    return await fetch(session, 'http://localhost:2000')


async def handle(request):
    session = aiohttp.ClientSession(connector=aiohttp.TCPConnector(limit=2))
    coroutines = [request_dependency(session) for _ in range(100)]
    responses = await asyncio.gather(*coroutines)
    await session.close()
    return web.Response(text=",".join(responses))

app = web.Application()
app.add_routes([web.get('/', handle)])

web.run_app(app, port=1995)
