package sd;

import java.util.List;

import com.cucumber.listener.Reporter;

import actions.APIUtility;
import actions.ActionClass;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import runner.Inputs;

public class GetLatestRatesSD {

	static int count = 0;
	static int status_code = 0;

	@Given("^the user performs the get call on the \"([^\"]*)\" foreign exchange rates$")
	public void the_user_performs_the_get_call_on_the_latest_foreign_exchange_rates(String resource) throws Throwable {
		Inputs.inputs();
		status_code = ActionClass.API_GetStatus("/"+resource);
		Reporter.addStepLog("Status Code= " + status_code);
		Assert.assertEquals(status_code, 200);

	}

	@Then("^displays all the foreign exchange rates$")
	public void displays_all_the_foreign_exchange_rates() throws Throwable {
		String rates = ActionClass.API_GetRates();
		Reporter.addStepLog(rates);
	}

	@Then("^verify base is \"([^\"]*)\"$")
	public void verify_base_is(String value) throws Throwable {
		JsonPath js = APIUtility.getValueJsonPath(ActionClass.responseBody.asString());
		Assert.assertEquals(value, js.getString("base"));
	}

	@Given("^the user performs the get call on the \"([^\"]*)\" foreign exchange rates based on \"([^\"]*)\"$")
	public void the_user_performs_the_get_call_on_the_latest_foreign_exchange_rates_based_on(String date, String queryparam,
			DataTable queryValues) throws Throwable {
		List<String> lst = queryValues.asList(String.class);
		status_code = ActionClass.API_GetStatus("/"+date, queryparam, lst);
		Assert.assertEquals(200, status_code);
	}

	@Then("^the user displays the filtered exchange rates$")
	public void the_user_displays_the_filtered_exchange_rates() throws Throwable {
		String rates = ActionClass.API_GetRates();
		Reporter.addStepLog(rates);
	}

	@Then("^verify rate for \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verify_rate_for_is(String base, String value) throws Throwable {

		boolean verifyValue = ActionClass.API_VerifyRates("/latest", base, value);
		Assert.assertTrue(verifyValue);

	}

	@Given("^the user performs the get call on the latest foreign exchange rates based on \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_user_performs_the_get_call_on_the_latest_foreign_exchange_rates_based_on_and(String base,
			String symbol, DataTable queryValues) throws Throwable {
		List<String> lst = queryValues.asList(String.class);
		status_code = ActionClass.API_GetStatus("/latest", base, symbol, lst);
	}

	@Given("^verify \"([^\"]*)\" is \"([^\"]*)\"$")
	public void verify_is(String basesymbol, String value) throws Throwable {
		JsonPath js = APIUtility.getValueJsonPath(ActionClass.responseBody.asString());
		if (basesymbol.equals("base"))
			Assert.assertEquals(value, js.getString(basesymbol));
		if(basesymbol.equals("date")) {
			String[] exactDate = APIUtility.getDate(value.toString().replace("|", "").trim()).split(":");
			Assert.assertEquals(exactDate[0], js.getString(basesymbol));
			Reporter.addStepLog(exactDate[1]);
			
			Reporter.addStepLog("Date from the API response is:" + js.getString(basesymbol) );
		}
		else if(basesymbol.equals("symbols"))
			Reporter.addStepLog(js.getString("rates"));
	}

}
