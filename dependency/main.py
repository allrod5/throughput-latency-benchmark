import asyncio

from aiohttp import web


async def handle(request):
    await asyncio.sleep(0.01)
    return web.Response(text="OK")

app = web.Application()
app.add_routes([web.get('/', handle)])

web.run_app(app, port=2000)
