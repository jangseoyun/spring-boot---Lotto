package lotto.practice.random.domain.lottoapi;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class FileRestApiServiceImpl implements RestApiService {
    @Override
    public String readUrl(String urlPath) {
        BufferedReader reader = null;
        try {
            return getString(urlPath, reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getString(String urlPath, BufferedReader reader) throws IOException {
        try {
            URL url = new URL(urlPath);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read = -1;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void run() {
        testMethod();
    }

    public void testMethod() throws RuntimeException{
        throw new IllegalStateException();
    }
}
