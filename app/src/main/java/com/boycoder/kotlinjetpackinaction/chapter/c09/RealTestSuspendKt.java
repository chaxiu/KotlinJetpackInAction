// TestSuspendKt.java
package com.boycoder.kotlinjetpackinaction.chapter.c09;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.RunSuspendKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 4, 0},
        bv = {1, 0, 3},
        k = 2,
        d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u0019\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u0011\u0010\u0006\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b\u001a\u0011\u0010\f\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0011\u0010\r\u001a\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"},
        d2 = {"getFeedList", "", "list", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFriendList", "user", "getUserInfo", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "log", "", "msg", "", "main", "testCoroutine", "app_debug"}
)
public final class RealTestSuspendKt {
    @Nullable
    public static final Object getUserInfo(@NotNull Continuation $completion) {
        /* 省略 */
        return "BoyCoder";
    }

    @Nullable
    public static final Object getFriendList(@NotNull String user, @NotNull Continuation $completion) {
        /* 省略 */
        return "Tom, Jack";
    }

    @Nullable
    public static final Object getFeedList(@NotNull String list, @NotNull Continuation $completion) {
        /* 省略 */
        return "{FeedList..}";
    }

    /**
     * 实际反编译的代码结构
     * 将其注释的原因是：它会导致编译报错
     * 但注释部分才是最重要的内容
     * @param $completion
     * @return
     */
/*
    @Nullable
    public static final Object testCoroutine(@NotNull Continuation $completion) {
        Object $continuation;
        label37: {
            if ($completion instanceof <TestSuspendKt$testCoroutine$1>) {
                $continuation = (<TestSuspendKt$testCoroutine$1>)$completion;
                if ((((<TestSuspendKt$testCoroutine$1>)$continuation).label & Integer.MIN_VALUE) != 0) {
                    ((<TestSuspendKt$testCoroutine$1>)$continuation).label -= Integer.MIN_VALUE;
                    break label37;
                }
            }

            $continuation = new ContinuationImpl($completion) {
                // $FF: synthetic field
                Object result;
                int label;
                Object L$0;
                Object L$1;

                @Nullable
                public final Object invokeSuspend(@NotNull Object $result) {
                    this.result = $result;
                    this.label |= Integer.MIN_VALUE;
                    return TestSuspendKt.testCoroutine(this);
                }
            };
        }

        Object var10000;
        label31: {
            String user;
            String friendList;
            Object var6;
            label30: {
                Object $result = ((<TestSuspendKt$testCoroutine$1>)$continuation).result;
                var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(((<TestSuspendKt$testCoroutine$1>)$continuation).label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        log("start");
                        ((<TestSuspendKt$testCoroutine$1>)$continuation).label = 1;
                        var10000 = getUserInfo((Continuation)$continuation);
                        if (var10000 == var6) {
                            return var6;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break;
                    case 2:
                        user = (String)((<TestSuspendKt$testCoroutine$1>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break label30;
                    case 3:
                        friendList = (String)((<TestSuspendKt$testCoroutine$1>)$continuation).L$1;
                        user = (String)((<TestSuspendKt$testCoroutine$1>)$continuation).L$0;
                        ResultKt.throwOnFailure($result);
                        var10000 = $result;
                        break label31;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }

                user = (String)var10000;
                log(user);
                ((<TestSuspendKt$testCoroutine$1>)$continuation).L$0 = user;
                ((<TestSuspendKt$testCoroutine$1>)$continuation).label = 2;
                var10000 = getFriendList(user, (Continuation)$continuation);
                if (var10000 == var6) {
                    return var6;
                }
            }

            friendList = (String)var10000;
            log(friendList);
            ((<TestSuspendKt$testCoroutine$1>)$continuation).L$0 = user;
            ((<TestSuspendKt$testCoroutine$1>)$continuation).L$1 = friendList;
            ((<TestSuspendKt$testCoroutine$1>)$continuation).label = 3;
            var10000 = getFeedList(friendList, (Continuation)$continuation);
            if (var10000 == var6) {
                return var6;
            }
        }

        String feedList = (String)var10000;
        log(feedList);
        return Unit.INSTANCE;
    }
*/
    @Nullable
    public static final Object main(@NotNull Continuation $completion) {
        /* 省略 */

        return Unit.INSTANCE;
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        /* 省略 */
    }

    public static final void log(@NotNull Object msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        StringBuilder var10000 = new StringBuilder();
        Thread var10001 = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(var10001, "Thread.currentThread()");
        String var1 = var10000.append(var10001.getName()).append(" msg=").append(msg).toString();
        boolean var2 = false;
        System.out.println(var1);
    }
}
// $FF: synthetic class
@Metadata(
        mv = {1, 4, 0},
        bv = {1, 0, 3},
        k = 3
)
final class TestSuspendKt$$$main extends Lambda implements Function1 {
    private final String[] args;

    // $FF: synthetic method
    TestSuspendKt$$$main(String[] var1) {
        super(1);
        this.args = var1;
    }

    // $FF: synthetic method
    public final Object invoke(Object var1) {
        return TestSuspendKt.main((Continuation)var1);
    }
}

