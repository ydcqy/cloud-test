import asyncio


async def abc():
    print("哈哈哈")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    tasks = [asyncio.ensure_future(abc()) for i in range(10)]
    loop.run_until_complete(asyncio.wait(tasks))
