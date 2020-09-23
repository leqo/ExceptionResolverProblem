package utils

import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class ThreadUtils {
    static <T> T withTimeLimit(long timeLimitInMillis, Closure<T> body) {
        T result = null

        ExecutorService executor = Executors.newSingleThreadExecutor()

        Future<?> future = executor.submit(new Runnable() {
            @Override
            void run() {
                result = body()
            }
        })

        executor.shutdown()

        try {
            future.get(timeLimitInMillis, TimeUnit.MILLISECONDS)
        } catch (ExecutionException e) {
            throw e.cause
        } catch (TimeoutException e) {
            future.cancel(true)
        }

        result
    }
}
