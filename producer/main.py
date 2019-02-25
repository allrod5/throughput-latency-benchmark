import time

import aiohttp
import asyncio
import statistics


async def fetch(session, url):
    async with session.get(url) as response:
        return await response.text()


async def main(loop):
    latencies = []
    expected_response = ','.join(['OK']*100)

    async def iterate():
        nonlocal latencies
        start = time.time()
        async with aiohttp.ClientSession() as session:
            response = await fetch(session, 'http://localhost:1995')
            assert response == expected_response
        latencies.append(time.time() - start)

    coroutines = [asyncio.create_task(iterate()) for _ in range(100)]
    await asyncio.gather(*coroutines)

    print(f"Mean Latency: {statistics.mean(latencies)}, Standard Deviation: {statistics.stdev(latencies)}")

loop = asyncio.get_event_loop()
loop.run_until_complete(main(loop))
