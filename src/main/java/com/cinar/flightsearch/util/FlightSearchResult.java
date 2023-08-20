package com.cinar.flightsearch.util;

import com.cinar.flightsearch.model.Flight;

import java.util.List;

public class FlightSearchResult {
    private Flight outboundFlight;
    private Flight returnFlight;

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }
}
