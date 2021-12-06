package challenges.cracking_coding_interview.object_oriented_design.call_center

import kotlin.jvm.JvmStatic

/* Employee is a super class for the Director, Manager, and Respondent classes. It is implemented as an
 * abstract class, since there should be no reason to instantiated an Employee type directly.
 */
abstract class Employee(private val callHandler: CallHandler) {
    private var currentCall: Call? = null
    var rank: Rank? = null
        protected set

    /* Start the conversation */
    fun receiveCall(call: Call?) {
        currentCall = call
    }

    /* the issue is resolved, finish the call */
    fun callCompleted() {
        if (currentCall != null) {
            /* Disconnect the call. */
            currentCall!!.disconnect()

            /* Free the employee */currentCall = null
        }

        /* Check if there is a call waiting in queue */assignNewCall()
    }

    /*
	 * The issue has not been resolved. Escalate the call, and assign a new call
	 * to the employee.
	 */
    fun escalateAndReassign() {
        if (currentCall != null) {
            /* escalate call */
            currentCall!!.incrementRank()
            callHandler.dispatchCall(currentCall!!)

            /* free the employee */currentCall = null
        }

        /* assign a new call */assignNewCall()
    }

    /* Assign a new call to an employee, if the employee is free. */
    fun assignNewCall(): Boolean {
        return if (!isFree) {
            false
        } else callHandler.assignCall(this)
    }

    /* Returns whether or not the employee is free. */
    val isFree: Boolean
        get() = currentCall == null
}