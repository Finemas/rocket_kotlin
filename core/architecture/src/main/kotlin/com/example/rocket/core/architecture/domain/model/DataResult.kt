package com.example.rocket.core.architecture.domain.model

/**
 * Represents a result of some operation.
 */
sealed class DataResult<out T> {
    companion object {
        /**
         * Creates a [DataResult] representing a successful operation.
         */
        fun <T> success(data: T): DataResult<T> = Success(data)

        /**
         * Creates a [DataResult] representing a failed operation.
         */
        fun <T> error(cause: Throwable): DataResult<T> = Error(cause)
    }

    /**
     * Represents a successful operation.
     */
    data class Success<out T> internal constructor(
        val data: T,
    ) : DataResult<T>()

    /**
     * Represents a failed operation.
     */
    data class Error internal constructor(
        val error: Throwable,
    ) : DataResult<Nothing>()
}

/**
 * Returns the encapsulated value if this instance represents [DataResult.Success],
 * or throws the encapsulated error if this instance represents [DataResult.Error].
 */
@Throws(Throwable::class)
fun <T> DataResult<T>.getOrThrow(): T {
    return when (this) {
        is DataResult.Success -> this.data
        is DataResult.Error -> throw this.error
    }
}

/**
 * Returns the encapsulated value if this instance represents [DataResult.Success],
 * or calls [errorBlock] and uses the value return from it.
 */
fun <T> DataResult<T>.getOrElse(errorBlock: (DataResult.Error) -> T): T {
    return when (this) {
        is DataResult.Success -> this.data
        is DataResult.Error -> errorBlock(this)
    }
}

/**
 * Calls the specified function [success] with the encapsulated value if this instance represents [DataResult.Success].
 */
inline fun <T> DataResult<T>.onSuccess(
    success: (data: T) -> Unit,
): DataResult<T> = fold(onSuccess = success)

/**
 * Calls the specified function [failure] with the encapsulated error if this instance represents [DataResult.Error].
 */
inline fun <T> DataResult<T>.onError(
    failure: (reason: Throwable) -> Unit,
): DataResult<T> = fold(onError = failure)

/**
 * Calls the specified function [onSuccess] with the encapsulated value if this instance represents [DataResult.Success],
 * or calls the specified function [onError] with the encapsulated error if this instance represents [DataResult.Error].
 */
inline fun <T> DataResult<T>.fold(
    onSuccess: (data: T) -> Unit = {},
    onError: (reason: Throwable) -> Unit = {},
): DataResult<T> {
    when (this) {
        is DataResult.Success -> onSuccess(data)
        is DataResult.Error -> onError(error)
    }
    return this
}

/**
 * Maps the value of this [DataResult] if it is [DataResult.Success].
 */
inline fun <T, R> DataResult<T>.map(
    transformation: (T) -> R,
): DataResult<R> {
    return when (this) {
        is DataResult.Success -> DataResult.success(transformation(data))
        is DataResult.Error -> this
    }
}

/**
 * Maps the value of this [DataResult] if it is [DataResult.Error].
 */
inline fun <T> DataResult<T>.mapError(
    transformation: (Throwable) -> Throwable,
): DataResult<T> {
    return when (this) {
        is DataResult.Success -> this
        is DataResult.Error -> DataResult.error(transformation(error))
    }
}

/**
 * If it is [DataResult.Success]. Then maps to new [DataResult].
 * Otherwise returns original [DataResult.Error]
 */
inline fun <T, R> DataResult<T>.flatMap(
    transformation: (value: T) -> DataResult<R>,
): DataResult<R> {
    return when (this) {
        is DataResult.Error -> this
        is DataResult.Success -> transformation(data)
    }
}

/**
 * Erase [DataResult] type to [Unit].
 */
fun <T> DataResult<T>.eraseType() = map { }

/**
 * Combines two [DataResult] instances into a single [DataResult] instance.
 * If both instances are successful, the [combineSuccess] function is called with the encapsulated values of both instances.
 * If at least one instance is an error, the [combineError] function is called with the errors from both instances or null if that instance
 * is [DataResult.Success].
 * By default, the errors are combined into a single exception with the original exceptions as suppressed exceptions.
 */
fun <T1, T2, R> combine(
    result1: DataResult<T1>,
    result2: DataResult<T2>,
    combineError: (DataResult.Error?, DataResult.Error?) -> DataResult.Error = ::combineErrors,
    combineSuccess: (T1, T2) -> R,
): DataResult<R> {
    return if (result1 is DataResult.Error || result2 is DataResult.Error) {
        combineError(result1 as? DataResult.Error, result2 as? DataResult.Error)
    } else {
        DataResult.success(combineSuccess((result1 as DataResult.Success).data, (result2 as DataResult.Success).data))
    }
}

private fun combineErrors(e1: DataResult.Error?, e2: DataResult.Error?): DataResult.Error {
    val throwable = if (e1 != null && e2 != null) {
        Exception("Multiple errors occurred").apply {
            addSuppressed(e1.error)
            addSuppressed(e2.error)
        }
    } else {
        e1?.error
            ?: e2?.error
            ?: IllegalStateException("Should not happen: combineError execution when combining DataResults without errors")
    }
    return DataResult.error<Nothing>(throwable) as DataResult.Error
}
