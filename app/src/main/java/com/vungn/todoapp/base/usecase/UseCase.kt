package vn.com.vti.learnningdisplay.base.usecase

interface UseCase<PARAMS, RESULT> {
    suspend fun execute(params: PARAMS): RESULT
}