package challenges.cracking_coding_interview.object_oriented_design.call_center

import java.util.ArrayList
import kotlin.jvm.JvmStatic

/* CallHandler represents the body of the program,
 * and all calls are funneled first through it. 
 */
class CallHandler {
    /* We have 3 levels of employees: respondents, managers, directors. */
    private val LEVELS = 3

    /* Initialize with 10 respondents, 4 managers, and 2 directors. */
    private val NUM_RESPONDENTS = 10
    private val NUM_MANAGERS = 4
    private val NUM_DIRECTORS = 2

    /* List of employees, by level.
     * employeeLevels[0] = respondents
     * employeeLevels[1] = managers
     * employeeLevels[2] = directors
     */
    var employeeLevels: MutableList<List<Employee>> = ArrayList(LEVELS)

    /* queues for each call�s rank */
    var callQueues: List<MutableList<Call>> = ArrayList(LEVELS)

    /* Gets the first available employee who can handle this call. */
    fun getHandlerForCall(call: Call): Employee? {
        for (level in call.rank.value until LEVELS - 1) {
            val employeeLevel = employeeLevels[level]
            for (emp in employeeLevel) {
                if (emp.isFree) {
                    return emp
                }
            }
        }
        return null
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    fun dispatchCall(caller: Caller) {
        val call = Call(caller)
        dispatchCall(call)
    }

    /* Routes the call to an available employee, or saves in a queue if no employee available. */
    fun dispatchCall(call: Call) {
        /* Try to route the call to an employee with minimal rank. */
        val emp = getHandlerForCall(call)
        if (emp != null) {
            emp.receiveCall(call)
            call.setHandler(emp)
        } else {
            /* Place the call into corresponding call queue according to its rank. */
            call.reply("Please wait for free employee to reply")
            callQueues[call.rank.value].add(call)
        }
    }

    /* An employee got free. Look for a waiting call that he/she can serve. Return true
     * if we were able to assign a call, false otherwise. */
    fun assignCall(emp: Employee): Boolean {
        /* Check the queues, starting from the highest rank this employee can serve. */
        for (rank in emp.rank!!.value downTo 0) {
            val que: MutableList<Call> = callQueues[rank]

            /* Remove the first call, if any */if (que.size > 0) {
                val call: Call = que.removeAt(0)
                emp.receiveCall(call)
                return true
            }
        }
        return false
    }

    init {

        // Create respondents.
        val respondents = ArrayList<Employee>(NUM_RESPONDENTS)
        for (k in 0 until NUM_RESPONDENTS - 1) {
            respondents.add(Respondent(this))
        }
        employeeLevels.add(respondents)

        // Create managers.
        val managers = ArrayList<Employee>(NUM_MANAGERS)
        managers.add(Manager(this))
        employeeLevels.add(managers)

        // Create directors.
        val directors = ArrayList<Employee>(NUM_DIRECTORS)
        directors.add(Director(this))
        employeeLevels.add(directors)
    }
}