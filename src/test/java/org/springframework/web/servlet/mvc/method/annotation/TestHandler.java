package org.springframework.web.servlet.mvc.method.annotation;

import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class TestHandler implements ResponseBodyEmitter.Handler {

    private final List<Object> objects = new ArrayList<>();

    private final List<MediaType> mediaTypes = new ArrayList<>();

    private int writeCount;


    public void assertSentObjectCount(int size) {
        assertThat(this.objects).hasSize(size);
    }

    public void assertObject(int index, Object object) {
        assertObject(index, object, null);
    }

    public void assertObject(int index, Object object, MediaType mediaType) {
        assertThat(index).isLessThanOrEqualTo(this.objects.size());
        assertThat(this.objects.get(index)).isEqualTo(object);
        assertThat(this.mediaTypes.get(index)).isEqualTo(mediaType);
    }

    public void assertWriteCount(int writeCount) {
        assertThat(this.writeCount).isEqualTo(writeCount);
    }

    @Override
    public void send(Object data, @Nullable MediaType mediaType) {
        this.objects.add(data);
        this.mediaTypes.add(mediaType);
        this.writeCount++;
    }

    @Override
    public void send(Set<ResponseBodyEmitter.DataWithMediaType> items) {
        for (ResponseBodyEmitter.DataWithMediaType item : items) {
            this.objects.add(item.getData());
            this.mediaTypes.add(item.getMediaType());
        }
        this.writeCount++;
    }

    @Override
    public void complete() {
    }

    @Override
    public void completeWithError(Throwable failure) {
    }

    @Override
    public void onTimeout(Runnable callback) {
    }

    @Override
    public void onError(Consumer<Throwable> callback) {
    }

    @Override
    public void onCompletion(Runnable callback) {
    }
}
