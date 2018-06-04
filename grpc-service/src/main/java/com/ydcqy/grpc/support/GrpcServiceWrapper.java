package com.ydcqy.grpc.support;

import io.grpc.BindableService;
import io.grpc.ServerServiceDefinition;

/**
 * @author xiaoyu
 */
public final class GrpcServiceWrapper implements BindableService {
    private BindableService grpcService;

    private void setGrpcService(BindableService grpcService) {
        this.grpcService = grpcService;
    }

    private GrpcServiceWrapper() {
    }

    public static GrpcServiceWrapper wrap(BindableService grpcService) {
        GrpcServiceWrapper wrapper = new GrpcServiceWrapper();
        wrapper.setGrpcService(grpcService);
        return wrapper;
    }

    @Override
    public ServerServiceDefinition bindService() {
        return grpcService.bindService();
    }
}
