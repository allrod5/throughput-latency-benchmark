import time

import aiohttp
import asyncio
import statistics

runs = []

async def fetch(session, url):
    async with session.get(url) as response:
        return await response.text()


async def main(loop):
    for i in range(3):
        latencies = []
        expected_response = ','.join(['OK']*100)

        async def iterate():
            nonlocal latencies
            start = time.time()
            async with aiohttp.ClientSession() as session:
                response = await fetch(session, 'http://localhost:1995')
                try:
                    assert response == expected_response
                except AssertionError as e:
                    print(e)
            latencies.append(time.time() - start)

        coroutines = [asyncio.create_task(iterate()) for _ in range(100)]
        await asyncio.gather(*coroutines)

        runs.append((statistics.mean(latencies), statistics.stdev(latencies), max(latencies)))

loop = asyncio.get_event_loop()
loop.run_until_complete(main(loop))

print(f"Mean Latency: {statistics.mean([run[0] for run in runs])}, Standard Deviation: {statistics.mean([run[1] for run in runs])}, Max Latency: {statistics.mean([run[2] for run in runs])}")
