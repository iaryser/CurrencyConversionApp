package ch.timor.projects.currencyconverter.model;

public class ConversionResponse {
    private String success;
    private String result;
    private Query query;
    private Info info;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Query getQuery() {
        return this.query;
    }

    public Info getInfo() {
        return this.info;
    }

    // !! ------------------ Nested class --------------------- !!(bc of gson)
    public static class Query {
        private String from;
        private String to;
        private String amount;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    // !! ------------------ Nested class --------------------- !!
    public static class Info {
        private String quote;

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }
    }
}
