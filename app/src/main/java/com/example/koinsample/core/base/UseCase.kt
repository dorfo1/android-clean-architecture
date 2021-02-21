import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in T,out O> : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend operator fun invoke(params : T) : O = execute(params)


    abstract suspend fun execute(params : T) : O
}