package sd;

import com.cucumber.listener.Reporter;

import actions.APIUtility;
import actions.ActionClass;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import junit.framework.Assert;
import runner.Inputs;

public class GetDateRatesSD {
	
	static int count = 0;
	static int status_code = 0;
	
	@Given("^the user performs the get call on the foreign exchange rates for date$")
	public void the_user_performs_the_get_call_on_the_foreign_exchange_rates_for_date(DataTable date) throws Throwable {
		Inputs.inputs();
		APIUtility.getDate(date.toString().replace("|", "").trim());
		status_code = ActionClass.API_GetStatus("/"+date.toString().replace("|", "").trim());
		Reporter.addStepLog("Status Code= " + status_code);
		Assert.assertEquals(status_code, 200);

	}

}
